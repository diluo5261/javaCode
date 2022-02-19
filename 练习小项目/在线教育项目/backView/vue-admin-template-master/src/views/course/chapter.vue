<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>

    <el-steps
      :active="2"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-button type="text" @click="openAddChapter()">添加章节</el-button>
    <!-- 章节 -->
    <ul class="chanpterList">
      <li v-for="chapter in chapterVideoList" :key="chapter.id">
        <p>
          {{ chapter.title }}
          <span class="acts">
            <el-button type="text" @click="openAddVideo(chapter.id)">添加小节</el-button>
            <el-button style type="text" @click="openUpdateChapter(chapter.id)"
              >编辑</el-button
            >
            <el-button type="text" @click="delChapterInfo(chapter.id)"
              >删除</el-button
            >
          </span>
        </p>

        <!-- 视频 -->
        <ul class="chanpterList videoList">
          <li v-for="video in chapter.children" :key="video.id">
            <p>
              {{ video.title }}
              <span class="acts">
                <el-button type="text" @click="openUpdateVideo(video.id)">编辑</el-button
                >
                <el-button type="text" @click="delVideoInfo(video.id)">删除</el-button
                >
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
        >下一步</el-button
      >
    </div>

    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="eduChapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="eduChapter.title" />
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number
            v-model="eduChapter.sort"
            :min="0"
            controls-position="right"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="eduVideo" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="eduVideo.title" />
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number
            v-model="eduVideo.sort"
            :min="0"
            controls-position="right"
          />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="eduVideo.isFree">
            <el-radio :label="1">免费</el-radio>
            <el-radio :label="0">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        
        <!-- 上传视频 begin -->
        <el-form-item label="上传视频">
          <el-upload
            :on-success="handleVodUploadSuccess"
            :on-remove="handleVodRemove"
            :before-remove="beforeVodRemove"
            :on-exceed="handleUploadExceed"
            :file-list="fileList"
            :action="BASE_API+'/eduvod/video/uploadVideo'"
            :limit="1"
            class="upload-demo"
          >
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">
                最大支持1G，
                <br>支持3GP、ASF、AVI、DAT、DV、FLV、F4V、
                <br>GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、
                <br>MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、
                <br>SWF、TS、VOB、WMV、WEBM 等视频格式上传
              </div>
              <i class="el-icon-question"/>
            </el-tooltip>
          </el-upload>
        </el-form-item>


<!-- 上传视频 end -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled"   type="primary"     @click="saveOrUpdateVideo">确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import chapter from "@/api/chapter";
import videoapi from "@/api/video";

export default {
  data() {
    return {
      courseId: "",
      saveBtnDisabled: false, // 保存按钮是否禁用
      chapterVideoList: [], //大纲列表数据
      dialogChapterFormVisible: false, //章节对话框是否显示
      eduChapter: {}, //章节表单

      saveVideoBtnDisabled: false, // 课时按钮是否禁用
      dialogVideoFormVisible: false, // 是否显示课时表单
      chapterId: "", // 课时所在的章节id
      eduVideo: {
        // 课时对象
        title: "",
        sort: 0,
        isFree: 0,
        videoSourceId: "",
      },
      fileList: [], //上传文件列表
      BASE_API: process.env.BASE_API // 接口API地址
    };
  },
  created() {
    console.log("chapter created");
    if (this.$route.params && this.$route.params.id) {
      this.courseId = this.$route.params.id;
      this.getChapterVideoList();
    }
  },
  methods: {
    //根据id查询章节\小节信息
    getChapterVideoList() {
      chapter.getChapterVideoById(this.courseId).then((response) => {
        this.chapterVideoList = response.data.chapterVoList;
      });
    },
    //打开添加章节的对话框
    openAddChapter() {
      this.eduChapter = {};
      this.dialogChapterFormVisible = true;
    },
    //添加\修改章节信息
    saveOrUpdate() {
      if (this.eduChapter.id) {
        //修改
        this.updataChapterInfo();
      } else {
        //添加
        this.saveChapter();
      }
    },
    //添加章节信息
    saveChapter() {
      //1.把课程id存入eduChapter
      this.eduChapter.courseId = this.courseId;

      //2.调用 api添加章节
      chapter.addChapter(this.eduChapter).then((response) => {
        this.$message.success("添加成功");
        //3.关闭章节对话框
        this.dialogChapterFormVisible = false;

        //4.刷新大纲页面
        this.getChapterVideoList();
      });
    },
    //修改章节信息
    updataChapterInfo() {
      //1.调用api修改章节信息
      chapter.updateChapter(this.eduChapter).then((response) => {
        this.$message.success("修改成功");

        //2.关闭章节对话
        this.dialogChapterFormVisible = false;

        //3.刷新大纲界面
        this.getChapterVideoList();
      });
    },
    //删除章节
    delChapterInfo(chapterId) {
      this.$confirm("此操作将永久删除该章节, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          chapter.delChapter(chapterId).then((response) => {
            this.$message.success("删除成功!");
            //刷新大纲
            this.getChapterVideoList();
          });
        })
        .catch(() => {
          this.$message.info("已取消删除");
        });
    },
    //打开修改章节窗口,回显数据
    openUpdateChapter(chapterId) {
      chapter.getChapterById(chapterId).then((response) => {
        this.eduChapter = response.data.eduChapter;
        this.dialogChapterFormVisible = true;
      });
    },
    //打开添加小节对话框
    openAddVideo(chapterIdFrom) {
      this.eduVideo={}
      this.dialogVideoFormVisible = true;
      this.chapterId = chapterIdFrom;

    },
    
    saveOrUpdateVideo(){
      if(this.eduVideo.id){
        this.updateVideoInfo()
      }else{
        this.saveVideo()
      }
    },
    saveVideo() {
      this.eduVideo.courseId = this.courseId;
      this.eduVideo.chapterId = this.chapterId;

      videoapi.addVideo(this.eduVideo).then((response) => {
        this.$message.success("小节添加成功");

        this.videoFlush();        
      });
    },
    openUpdateVideo(videoId) {
      this.dialogVideoFormVisible = true;

     videoapi.getVideoById(videoId).then(response=>{
        this.eduVideo = response.data.eduVideo
      })

    },
    updateVideoInfo(){
      videoapi.updateVideo(this.eduVideo).then(response=>{
        this.$message.success("修改成功!")
        this.videoFlush()
      })
    },
    //关闭video对话框,刷新大纲,重置video
    videoFlush(){
      this.dialogVideoFormVisible = false; // 如果保存成功则关闭对话框
        this.getChapterVideoList(); // 刷新大纲
        this.eduVideo.title = ""; // 重置章节标题
        this.eduVideo.sort = 0; // 重置章节标题
        this.eduVideo.videoSourceId = ""; // 重置视频资源id
        this.saveVideoBtnDisabled = false;

    },
    //根据id删除小节信息
    delVideoInfo(videoId) {
      this.$confirm("此操作将永久删除该小节, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        videoapi.delVideo(videoId).then(()=>{
          this.$message.success("删除成功!")
          this.getChapterVideoList()
        })
         
      }).catch(() => {
        this.$message.info("取消删除")
          
      })
    },
    //成功回调
    handleVodUploadSuccess(response, file, fileList) {
      this.eduVideo.videoSourceId = response.data.videoId;
    },
    //视图上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning("想要重新上传视频，请先删除已上传的视频");
    },

    previous() {
      console.log("previous");
      this.$router.push({ path: `/course/add/${this.courseId}` });
    },
    next() {
      console.log("next");
      this.$router.push({ path: `/course/publish/${this.courseId}` });
    },
  },
};
</script>

<style scoped>
.chanpterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li {
  position: relative;
}
.chanpterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #ddd;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}

.videoList {
  padding-left: 50px;
}
.videoList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #ddd;
}
</style>
