一、引入依赖：

        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-xml</artifactId>
            <version>2.8.8</version>
        </dependency>
   
二、新建入参参数对象

    @JacksonXmlRootElement(localName ="message")
    public class WorkerListRequest {
    
        @JacksonXmlElementWrapper(localName ="workerList")
        @JacksonXmlProperty(localName ="worker")
        private List<WorkerRequest> orderList;
    
    }
    
--

    public class WorkerRequest {
    
        //人员编号
        @JacksonXmlProperty(localName = "pernr")
        private String pernr;
    
        //所属部门编码
        @JacksonXmlProperty(localName = "orgeh")
        private String orgeh;
    
        //人员姓名
        @JacksonXmlProperty(localName = "sname")
        private String sname;
    
        //人员状态(0离职，1在职）
        @JacksonXmlProperty(localName = "stat2")
        private Integer stat2;
    
    }
    
    
三、编写接口
    
        @PostMapping(value = "/dataMove", consumes = {MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_XML_VALUE)
        @ResponseBody
        public WorkerResponse dataMove(@RequestBody WorkerListRequest workerListRequest) {
            return tWorkerService.dataMove(workerListRequest);
        }      
        
四、入参

    <?xml version="1.0" encoding="utf-8"?>
    
    <message> 
      <workerList> 
        <worker> 
          <pernr>2004</pernr>
          <orgeh>2</orgeh>
          <sname>2</sname>
          <stat2>2</stat2>
        </worker> 
        <worker> 
          <pernr>1120304</pernr>
          <orgeh>1</orgeh>
          <sname>1</sname>
          <stat2>1</stat2>
        </worker> 
      </workerList> 
    </message>