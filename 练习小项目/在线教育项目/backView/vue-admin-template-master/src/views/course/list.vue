<template>
  <div class="app-container">

     <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.teacherName" placeholder="讲师名" />
      </el-form-item>

      <el-form-item>
        <el-input v-model="courseQuery.courseName" placeholder="课程名" />
      </el-form-item>

    
      <el-form-item label="添加时间">
        <el-date-picker
          v-model="courseQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="courseQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button
        type="primary"
        icon="el-icon-search"
        @click="getCoursePageQuery()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    
    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
      row-class-name="myClassList"    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">{{
          (page - 1) * limit + scope.$index + 1
        }}</template>
      </el-table-column>

      <el-table-column label="课程信息" width="470" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img :src="scope.row.cover" alt="scope.row.title" width="150px" />
            </div>
            <div class="title">
              <a href>{{ scope.row.title }}</a>
              <p>{{ scope.row.lessonNum }}课时</p>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">{{
          scope.row.gmtCreate.substr(0, 10)
        }}</template>
      </el-table-column>
      <el-table-column label="发布时间" align="center">
        <template slot-scope="scope">{{
          scope.row.gmtModified.substr(0, 10)
        }}</template>
      </el-table-column>
      <el-table-column label="价格" width="100" align="center">
        <template slot-scope="scope">
          {{
            Number(scope.row.price) === 0
              ? "免费"
              : "¥" + scope.row.price.toFixed(2)
          }}
        </template>
      </el-table-column>
      <el-table-column
        prop="buyCount"
        label="付费学员"
        width="100"
        align="center"      >
        <template slot-scope="scope">{{ scope.row.buyCount }}人</template>
      </el-table-column>

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/add/' + scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">编辑课程信息</el-button>
          </router-link>
          <router-link :to="'/course/chapter/' + scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">编辑课程大纲</el-button>
          </router-link>
          <el-button
            type="text"
            size="mini"
            @click="delCourse(scope.row.id)"
            icon="el-icon-delete"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getCoursePage"
    />
  </div>
</template>


<script>
import course from "@/api/course"
export default {

    data(){
        return {
            courseQuery: {},
            listLoading:true,//是否显示loading信息
            list:[], //课程数据列表
            page:1,//当前页码
            limit:2, //每页记录数
            total:0 //总记录数

        }
    },
    created(){
        // this.getCourseList()
        this.getCoursePageQuery()

    },
    methods:{
        getCoursePageQuery(page =1){
          this.page = page
          course.getCoursePageQuery(this.page,this.limit,this.courseQuery).then(response=>{
            this.list = response.data.list
            this.total = response.data.total
            this.listLoading = false
          })

        },
        resetData(){
          this.courseQuery={}
          this.getCoursePage()
        },
        getCourseList(){
            course.getCourseInfo().then(response=>{
                this.list = response.data.courseList
                this.listLoading = false
            })
        },
        //删除课程
        delCourse(id){
            this.$confirm("此操作将永久删除该课程信息,确认是否删除",{
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"

            }).then(response=>{
                course.delCourseInfo(id).then(response=>{
                    this.$message.success("删除成功")
                    //刷新页码
                     this.getCourseList()
                })

            }).catch(()=>{
                this.$message.error("取消删除")
            })
        }


    }
    //TODO 分页查询
    
}
</script>