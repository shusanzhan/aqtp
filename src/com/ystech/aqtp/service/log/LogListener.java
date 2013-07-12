package com.ystech.aqtp.service.log;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.event.spi.AbstractEvent;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.OperateLog;
import com.ystech.aqtp.model.User;
import com.ystech.core.util.IpUtil;
import com.ystech.core.util.SecurityUserHolder;


@SuppressWarnings("serial")
@Component("logListener")
public class LogListener  implements PostInsertEventListener,PostUpdateEventListener,PostDeleteEventListener{
	private final static String filePath = "/log.record.xml";
	private static Document doc;
	static {
		if (doc == null) {	
			SAXReader saxReader = new SAXReader();
            InputStream is = LogListener.class.getResourceAsStream(filePath);
            try {
                doc = saxReader.read(is);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	/**
	 * 添加数据
	 */
	public void onPostInsert(PostInsertEvent event) {
		try{
			//设置操作日志对象
	        String actionObj = event.getEntity().getClass().getName();
	        actionObj = getChinaName(actionObj);
	        if (actionObj == null || actionObj.equals("")) return;
	        String actionType = "新增";
	        OperateLog log = new OperateLog();
	        log.setOperateobj(actionObj);
	        log.setOperatetype(actionType);
	        String actionStr = this.getActionStr(event.getEntity());
	        log.setOperatefeild(actionStr);
	        this.saveLog(event,log);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除数据
	 */
	public void onPostDelete(PostDeleteEvent event) {
		try {
			//设置操作日志对象
	        String actionObj = event.getEntity().getClass().getName();
	        
	        actionObj = getChinaName(actionObj);
	        if (actionObj == null || actionObj.equals("")) return;
	        String actionType = "删除数据";
	        
	        OperateLog log = new OperateLog();
	        log.setOperateobj(actionObj);
	        log.setOperatetype(actionType);
	        String actionStr = this.getActionStr(event.getEntity());
	        log.setOperatefeild(actionStr);
	        this.saveLog(event,log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改数据
	 */
	public void onPostUpdate(PostUpdateEvent event) {
		try {
			//设置操作日志对象
	        String actionObj = event.getEntity().getClass().getName();
	        
	        actionObj = getChinaName(actionObj);
	        if (actionObj == null || actionObj.equals("")) return;
	        String actionType = "更新";
	        
	        OperateLog log = new OperateLog();
	        log.setOperateobj(actionObj);
	        log.setOperatetype(actionType);
	        String actionStr = this.getActionStr(event.getEntity());
	        log.setOperatefeild(actionStr);
	        this.saveLog(event,log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存日志
	 * @param event
	 * @param operateLog
	 */
	public static void saveLog(AbstractEvent event,OperateLog operateLog){
		if (operateLog==null) {
			return ;
		}
		if(operateLog.getOperateobj()==null||operateLog.getOperateobj().trim().equals("")){
			return ;
		}
		operateLog.setOperatedate(new Date());
		User currentUser = SecurityUserHolder.getCurrentUser();
		IpUtil ipUtil=new IpUtil();
		String ipAddr = ipUtil.getIpAddr();
		operateLog.setIpAddress(ipAddr);
		if (currentUser!=null) {
			operateLog.setUserId(currentUser.getDbid());
			operateLog.setOperator(currentUser.getRealName());
		}
		event.getSession().save(operateLog);
	}
	//根据类全路径获取对应的中文
	private String getChinaName (String className) {
		Element log = getElementById(className);
		if(null!=log){
			return log.attributeValue("txt");
		}
		return null;
	}
	
	/**
     * 根据节点属性id的值，获取某个节点
     * @param id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private static Element getElementById(String id) {
        Element el = null;
        Element element = doc.getRootElement();
        List<Element> logs = element.elements();
        for (Element log : logs) {
        	if (id.equals(log.attributeValue("class"))) {
        		el = log;
        		break;
        	}
        }
        if (el != null) return el;
        else {
        	System.err.println("id为" + id + "的节点没有找到！");
        	return null;
        }
        	
        //throw new RuntimeException("id为" + id + "的节点没有找到！");
    }
    
  //根据类全路径获取对应属性名和属性中文
	@SuppressWarnings("unchecked")
	private Map<String,Object> getFeildsName (String className) {
		Map<String,Object> feilds = new TreeMap<String, Object>();
		Element log = getElementById(className);
		
		List<Element> feildsEles = log.elements();
		if (feildsEles != null && feildsEles.size() > 0) {
	        for (Element feild : feildsEles) {
	        	String feildName = feild.attributeValue("property");
	        	if (feildName == null || "".equals(feildName)) continue;
	        	//获取属性对应的中文对应值
	        	String feildVal = feild.attributeValue("value");
	        	if (feildVal !=null && !"".equals(feildVal)) {
	        		//String chinaName = feild.getTextTrim();
		        	feilds.put(feildName, feildVal);
	        	} else {//处理比较值标签
	        		feilds.put(feildName, feild);
	        	}
	        }
		}
		return feilds;
	}
	//设置唯一标示值
	@SuppressWarnings("unchecked")
	private String getActionStr(Object obj) throws Exception {
		String actionStr = "";
		
		//记录标识字段
        Map<String,Object> fields = this.getFeildsName(obj.getClass().getName());
        Set<String> keys = fields.keySet();
        for(String key : keys) {
        	Field keyObj = obj.getClass().getDeclaredField(key);
        	keyObj.setAccessible(true);
        	if (keyObj == null) continue;
        	Object newValue = keyObj.get(obj);
        	keyObj.setAccessible(false);
        	
        	Object actionProperty = fields.get(key);
        	if (actionProperty != null) {
        		if (actionProperty.getClass() == String.class) {//单个值对应
        			actionStr += actionProperty.toString()+"【"+newValue+"】,";
        		} else {//处理比较值
        			Element ele = (Element) actionProperty;
        			List<Element> equalsEle = ele.elements();
        			if (equalsEle != null && equalsEle.size() > 0) {
        				for (Element equalsFeild : equalsEle) {
        					String equalsValue = equalsFeild.attributeValue("value");
        					String value = equalsFeild.getTextTrim();
        					String field = equalsFeild.attributeValue("field");
        					
        					Field fieldObj = obj.getClass().getDeclaredField(field);
        					fieldObj.setAccessible(true);
        					if (fieldObj == null) continue;
        					
        					Object objValue = fieldObj.get(obj);
        					if (equalsValue != null && equalsValue.equals(objValue))
        						actionStr += value+"【"+newValue+"】,";
        					fieldObj.setAccessible(false);
        				}
        			}
        		}
        	}
        } 
        	
        if (!"".equals(actionStr)) actionStr = actionStr.substring(0, actionStr.length()-1);
        return actionStr;
	}
    public static void main(String[] args) {
		LogListener listener = new LogListener();
		String chinaName = listener.getChinaName("com.ystech.modo.model.VerificationCode");
		System.out.println(chinaName+"===");
		Map<String,Object>  ms= listener.getFeildsName ("com.ystech.modo.model.VerificationCode");
		Object obj = ms.get("dbid");
		if (obj != null && obj.getClass() == String.class) {
			System.out.println(obj.toString());
		}
		System.out.println(obj.getClass());
	}
}
