一、pom文件依赖配置
    
            <dependency>
                <groupId>com.ice</groupId>
                <artifactId>ice-tool</artifactId>
                <version>1.4.4</version>
                <scope>system</scope>
                <systemPath>${project.basedir}/lib/ice-tool-1.4.4.jar</systemPath>
            </dependency>
            
${project.basedir}：当前项目根目录
ice-tool-1.4.4.jar：引入jar包名