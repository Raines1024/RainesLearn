一、引入依赖：

            <!--webService-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web-services</artifactId>
            </dependency>
            <!-- apache-cxf -->
            <dependency>
                <groupId>org.apache.cxf</groupId>
                <artifactId>cxf-spring-boot-starter-jaxws</artifactId>
                <version>3.2.4</version>
            </dependency>
   
二、配置扫描"MetalService"服务

    @Configuration
    public class MetalConfig implements WebMvcConfigurer {
    
        @Autowired
        private Bus bus;
    
        @Autowired
        private MetalService metalService;
    
        @Bean
        public EndpointImpl endpoint() {
            EndpointImpl endpoint = new EndpointImpl(bus, metalService);
            endpoint.publish("/MetalService");
            return endpoint;
        }
    
    }

    
三、编写"MetalService"服务（OrgListRequest和OrgResponse是以xml格式接收的对象）
    
    @WebService(name = "MetalService", // 暴露服务名称
            targetNamespace = "http://server.hr.work.lovol.com"// 命名空间,一般是接口的包名倒序
    )
    public  interface MetalService {
    
        /**
         * 和hr系统对接机构数据
         * @param message
         * @return
         */
        @WebMethod
        @WebResult(name = "message", targetNamespace = "")
        OrgResponse sendOrgData(@WebParam(name = "message") OrgListRequest message);
    
    }

编写实现类

    @WebService(targetNamespace = "http://server.hr.work.lovol.com",//wsdl命名空间
            serviceName = "MetalService",//portType名称 客户端生成代码时 为接口名称
            endpointInterface = "com.lovol.work.hr.server.MetalService") //指定发布webservcie的接口类，此类也需要接入@WebService注解
    @Component
    public class MetalServiceImpl implements MetalService {
    
        @Resource
        private TOrgService tOrgService;
    
        /**
         * 和hr系统对接机构数据
         *
         * @param message
         * @return
         */
        @Override
        public OrgResponse sendOrgData(OrgListRequest message) {
            return tOrgService.dataMove(message);
        }
    
    
    }

        
四、参数类

    public class OrgListRequest {
    
        private List<OrgRequest> org;
  
    }
    


    public class OrgRequest {

        //部门编码
        @JacksonXmlProperty(localName = "objid")
        private String objid;
    
        //部门名称
        @JacksonXmlProperty(localName = "stext")
        private String stext;
    
        // 上级部门编码
        @JacksonXmlProperty(localName = "parentCode")
        private String parentCode;
    
        // 状态
        @JacksonXmlProperty(localName = "status")
        private Integer status;
    
    }
    
---

    public class OrgResponse {
    
        private int code;
    
        private String msg;
    
    }

五、xml数据结构
入参：

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://server.hr.work.lovol.com">
       <soapenv:Header/>
       <soapenv:Body>
          <ser:sendOrgData>
             <!--Optional:-->
             <message>
                <!--Zero or more repetitions:-->
                <org>
                   <!--Optional:-->
                   <objid>88</objid>
                   <!--Optional:-->
                   <parentCode>1</parentCode>
                   <!--Optional:-->
                   <status>1</status>
                   <!--Optional:-->
                   <stext>测试2</stext>
                </org>
                <org>
                   <!--Optional:-->
                   <objid>899</objid>
                   <!--Optional:-->
                   <parentCode>1</parentCode>
                   <!--Optional:-->
                   <status>1</status>
                   <!--Optional:-->
                   <stext>测试</stext>
                </org>
             </message>
          </ser:sendOrgData>
       </soapenv:Body>
    </soapenv:Envelope>
    
    
出参：

    <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
       <soap:Body>
          <ns2:sendOrgDataResponse xmlns:ns2="http://server.hr.work.lovol.com">
             <message>
                <code>200</code>
                <msg>更新部门数据成功</msg>
             </message>
          </ns2:sendOrgDataResponse>
       </soap:Body>
    </soap:Envelope>