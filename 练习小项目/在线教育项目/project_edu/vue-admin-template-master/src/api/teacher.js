import request from '@/utils/request'

export default {
    //带条件分页查询讲师信息
    getTeacherPageVo(current, limit, teacherQuery) {

        return request({
            url: `/eduservice/eduteacher/getpageVo/${current}/${limit}`,
            method: 'post',
            data: teacherQuery //转化成json串进行传递
        })
    },
    //删除讲师
    delTeacher(id) {
        return request({
            url: `/eduservice/eduteacher/${id}`,
            method: 'delete',
        })
    },
    // 添加讲师
    addTeacher(teacher) {
        return request({
            url: `/eduservice/eduteacher/addTeacher`,
            method: 'post',
            data: teacher //转化成json串进行传递

        })

    },
    getTeacherById(id){
        return request({
            url: `/eduservice/eduteacher/getTeacherById/${id}`,
            method: 'get'
        })

    },
    updateTeacher(eduTeacher){
        return request({
            url: `/eduservice/eduteacher/updateTeacher`,
            method: 'post',
            data: eduTeacher
        })

    }



}