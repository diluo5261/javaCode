import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.R;
import org.junit.Test;

public class VodTest {

    public  DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    @Test
    public void delTest(){
        try {
            //*初始化客户端对象
            DefaultAcsClient client = initVodClient("LTAI5tPsCYBzsLf8ZqkB5XTu",
                    "BKtwPPpvgwVPweNYiIjdDQuJnjGN5v");
            //*创建请求对象（不同操作，类不同）
            DeleteVideoRequest request = new DeleteVideoRequest();
            //*创建响应对象（不同操作，类不同）
            //DeleteVideoResponse response = new DeleteVideoResponse();
            //*向请求中设置参数
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds("ad876ce28cb14883bf2f0c31173889c9");
            //*调用客户端对象方法发送请求，拿到响应
            client.getAcsResponse(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DiloException(20001,"删除视频失败");
        }
    }
}
