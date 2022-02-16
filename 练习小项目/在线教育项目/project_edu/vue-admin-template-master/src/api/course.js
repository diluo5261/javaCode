import request from '@/utils/request'

export default {
    //带条件分页查询讲师信息
    addCourseInfo(courseInfoForm) {
        return request({
            url: `/eduservice/educourse/addCourseInfo`,
            method: 'post',
            data: courseInfoForm //转化成json串进行传递
        })
    },
    //根据id查询课程信息
    getCourseInfoById(id){
        return request({
            url: `/eduservice/educourse/getCourseInfoById/${id}`,
            method: 'get'
        })
    },
    updateCourseInfo(courseInfoForm){
        return request({
            url: `/eduservice/educourse/updateCourseInfo`,
            method: 'post',
            data: courseInfoForm //转化成json串进行传递
        })
    }

}