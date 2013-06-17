package runtime;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.spi.UpdateTimestampsCache;

public class TomcatServer {

	private final Log log = LogFactory.getLog(TomcatServer.class);
	private static final Integer DEFAULT_PORT = 8091;// 端口
	private static final String DEFAULT_APP_PATH = "/WebRoot";// 配置项目的发布目录名称
	private static final String APP_CONTEXT_PATH = "";// 配置项目的访问名称

	/**
	 * 返回当前项目的绝对路径
	 * 
	 * @return
	 */
	protected String getTomcatPath() {
		return System.getProperty("user.dir") + "/embbedserver";
	}

	/**
	 * 返回当前应用项目，用于发布到tomcat的应用存放路径(一般为项目名/webroot目录)
	 * 
	 * @return
	 */
	private String getSingleContextAbsolutePath() {
		String path = System.getProperty("user.dir") + DEFAULT_APP_PATH;
		return path;
	}

	public void start() throws Exception {
		Tomcat tomcat = new Tomcat();
		System.out.println(getTomcatPath());
		tomcat.setBaseDir(getTomcatPath()); // Embeded tomcat存放路径
		tomcat.setPort(DEFAULT_PORT);
		try {
			tomcat.addWebapp(APP_CONTEXT_PATH, getSingleContextAbsolutePath());// 应用存放路径
		} catch (ServletException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		try {
			long startTime = System.currentTimeMillis();
			tomcat.start(); // 启动
			System.out.println("http://"
					+ java.net.InetAddress.getLocalHost().getHostAddress()
					+ ":" + DEFAULT_PORT + APP_CONTEXT_PATH);
			System.err.println("Embedded Tomcat started in "
					+ (System.currentTimeMillis() - startTime) + " ms.");
		} catch (LifecycleException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		log.info("Tomcat started.");
		tomcat.getServer().await(); // 这个一定要
	}

	public static void main(String[] args) {
		try {
			new TomcatServer().start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}