import request from '@/utils/request'

//生成统计数据
export default{
  createStaDaily(day){
    return request({
      url: `/staService/stadaily/createStaDaily/${day}`,
      method: 'post'
    })
  },
  getStaDaily(searchObj) {
    return request({
      url: `/staService/stadaily//getStaDaily/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
      method: 'get'
    })
  }
}
