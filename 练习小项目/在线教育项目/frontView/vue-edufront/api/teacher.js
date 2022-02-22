import request from '@/utils/request'

export default {
    
    //分页查询讲师信息
    getTeacherApiPage(current,limit){
        return request({
            url: `/eduservice/teacerapi/getTeacherApiPage/${current}/${limit}`,
            method: 'get'
          })
    },
    //前台查询讲师详情
    getTeacherCourseById(id){
        return request({
            url: `/eduservice/teacerapi/getTeacherCourseById/${id}`,
            method: 'get'
          })
    }
}

