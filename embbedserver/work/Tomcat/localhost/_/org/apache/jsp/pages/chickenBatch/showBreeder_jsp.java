/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.23
 * Generated at: 2013-11-07 13:47:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.pages.chickenBatch;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class showBreeder_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/pages/chickenBatch/../commons/taglib.jsp", Long.valueOf(1353591198096L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  \r\n");
      if (_jspx_meth_c_005fset_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f1(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');

response.setHeader("Pragma", "No-cache"); 
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0); 

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/list.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/style.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("<link  href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/easyvalidator/css/validate.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/jqueryui/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/utile/utile.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/easyvalidator/js/jquery.bgiframe.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/easyvalidator/js/easy_validator.pack.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/My97DatePicker/WdatePicker.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/SWFUpload/swfupload.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/SWFUpload/plugins/swfupload.queue.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/SWFUpload/plugins/swfupload.speed.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/SWFUpload/js/fileupload.handlers.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var upload1;\r\n");
      out.write("\r\n");
      out.write("window.onload = function() {\r\n");
      out.write("\tupload1 = new SWFUpload(\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t// Backend Settings\r\n");
      out.write("\t\t\t\tupload_url : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/swfUpload/uploadFile\",\r\n");
      out.write("\t\t\t\tpost_params : {\r\n");
      out.write("\t\t\t\t\t\"PHPSESSID\" : \"6a95034fff6ba3a6aa8a990ca3af42ee\",\"userId\":\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.user.dbid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t//上传文件的名称\r\n");
      out.write("\t\t\t\tfile_post_name : \"file\",\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// File Upload Settings\r\n");
      out.write("\t\t\t\tfile_size_limit : \"5242880\", // 200MB\r\n");
      out.write("\t\t\t\tfile_types : \"*.jpg\",\r\n");
      out.write("\t\t\t\tfile_types_description : \"All Files\",\r\n");
      out.write("\t\t\t\tfile_upload_limit : \"1\",\r\n");
      out.write("\t\t\t\tfile_queue_limit : \"0\",\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// Event Handler Settings (all my handlers are in the Handler.js file)\r\n");
      out.write("\t\t\t\tfile_dialog_start_handler : fileDialogStart,\r\n");
      out.write("\t\t\t\tfile_queued_handler : fileQueued,\r\n");
      out.write("\t\t\t\tfile_queue_error_handler : fileQueueErrorHandler,\r\n");
      out.write("\t\t\t\tfile_dialog_complete_handler : fileDialogComplete,\r\n");
      out.write("\t\t\t\tupload_start_handler : uploadStart,\r\n");
      out.write("\t\t\t\tupload_progress_handler : uploadProgress,\r\n");
      out.write("\t\t\t\tupload_error_handler : uploadError,\r\n");
      out.write("\t\t\t\tupload_success_handler : uploadSuccess,\r\n");
      out.write("\t\t\t\tupload_complete_handler : uploadComplete,\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// Button Settings\r\n");
      out.write("\t\t\t\tbutton_image_url : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/SWFUpload/images/XPButtonUploadText_61x22.png\",\r\n");
      out.write("\t\t\t\tbutton_placeholder_id : \"spanButtonPlaceholder1\",\r\n");
      out.write("\t\t\t\tbutton_width : 61,\r\n");
      out.write("\t\t\t\tbutton_height : 22,\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// Flash Settings\r\n");
      out.write("\t\t\t\tflash_url : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/widgets/SWFUpload/Flash/swfupload.swf\",\r\n");
      out.write("\r\n");
      out.write("\t\t\t\tcustom_settings : {\r\n");
      out.write("\t\t\t\t\tprogressTarget : \"uploadFileContent\",\r\n");
      out.write("\t\t\t\t\tcancelButtonId : \"btnCancel1\"\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t// Debug Settings\r\n");
      out.write("\t\t\t\tdebug : false\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<title>查看饲养员信息</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"bodycolor\">\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<form action=\"\" name=\"frmId\" id=\"frmId\" style=\"margin-bottom: 40px;\" target=\"_self\">\r\n");
      out.write("\t\t<table border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 92%;\">\r\n");
      out.write("\t\t\t<tr height=\"42\">\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">用户ID:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td ><input  readonly=\"readonly\" type=\"text\" name=\"user.userId\" id=\"userId\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userId }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"input-xmedium field\" title=\"用户ID\" url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/validateUser\"\tcheckType=\"string,5,20\" tip=\"用户名不能为空,并且5-20个字符\"></td>\r\n");
      out.write("\t\t\t\t<td rowspan=\"4\" colspan=\"2\">\r\n");
      out.write("\t\t\t\t<table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"200\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td height=\"200\" width=\"\">\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"hidden\" name=\"breeder.photo\" id=\"fileUpload\" readonly=\"readonly\"\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.photo }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t\t\t\t\t\t\t<img alt=\"\" id=\"fileUploadImage\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.photo }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" width=\"300\" height=\"180\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t </td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr height=\"42\">\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">姓名:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td ><input type=\"text\" name=\"user.realName\" id=\"realName\"  readonly=\"readonly\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.realName }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"input-xmedium field\" title=\"姓名\"\tcheckType=\"string,1,10\" tip=\"真实名称不能为空\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr height=\"42\">\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">生日:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td ><input type=\"text\" name=\"breeder.birthday\" id=\"birthday\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.birthday }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"  readonly=\"readonly\" class=\"input-xmedium field\" onFocus=\"WdatePicker({isShowClear:false,readOnly:true})\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr height=\"32\">\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">性别:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" id=\"sex1\"  name=\"breeder.sex\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.sex=='男'?'checked=\"checked\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("  value=\"男\"><label id=\"\" for=\"sex1\">男</label>\r\n");
      out.write("\t\t\t\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" id=\"sex2\"  name=\"breeder.sex\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.sex=='女'?'checked=\"checked\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" value=\"女\"><label id=\"\" for=\"sex2\">女</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr height=\"32\">\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">手机:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"user.mobilePhone\" id=\"mobilePhone\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.mobilePhone }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"  readonly=\"readonly\" class=\"input-xmedium field\"  checkType=\"phoneNo\" canEmpty=\"Y\" tip=\"请输入正确的手机号\"></td>\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">座机:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"user.phone\" id=\"phone\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.phone }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"  readonly=\"readonly\" class=\"input-xmedium field\"  checkType=\"phoneNo\"  canEmpty=\"Y\" tip=\"请输入正确的座机号\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr height=\"42\">\r\n");
      out.write("\t\t\t    <td class=\"formTableTdLeft\" style=\"width: 60px;\">邮箱:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td ><input type=\"text\"  readonly=\"readonly\" name=\"user.email\" id=\"email\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.email }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"input-xmedium field\" title=\"邮箱\"\tcheckType=\"email\" canEmpty=\"Y\" tip=\"请输入正确的邮箱\"></td>\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">学历:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t<select  class=\"select field\" id=\"educationalBackground\" name=\"breeder.educationalBackground\" style=\"width: 120px;\">\r\n");
      out.write("\t\t\t\t\t\t<option value=\"小学\"  ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='小学'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" >小学</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"初中生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='初中生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">初中生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"高中生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='高中生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">高中生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"中专生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='中专生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">中专生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"大专生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='大专生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">大专生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"本科生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='本科生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">本科生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"硕士生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='硕士生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">硕士生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"博士生\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='博士生'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">博士生</option>\r\n");
      out.write("\t\t\t\t\t\t<option value=\"其它\" ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.educationalBackground=='其它'?'selected=\"selected\"':'' }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(">其它</option>\r\n");
      out.write("\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr height=\"42\">\r\n");
      out.write("\t\t\t\t<td class=\"formTableTdLeft\" style=\"width: 60px;\">毕业学校:&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td colspan=\"3\"><input  readonly=\"readonly\" type=\"text\" name=\"breeder.graduationSchool\" id=\"graduationSchool\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${breeder.graduationSchool }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"input-xlarge field\" title=\"毕业学校\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t<div class=\"buttons\" style=\"margin-top: 20px;\">\r\n");
      out.write("\t\t   <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ctx }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/chickenBatch/index?dbid=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.chickenBatchDbid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" target=\"contentUrl\"\tclass=\"ui-state-default\">关闭</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent(null);
    // /pages/chickenBatch/../commons/taglib.jsp(6,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("ctx");
    // /pages/chickenBatch/../commons/taglib.jsp(6,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath=='/'?'':pageContext.request.contextPath}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent(null);
    // /pages/chickenBatch/../commons/taglib.jsp(7,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("checked");
    // /pages/chickenBatch/../commons/taglib.jsp(7,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue(new String("checked=\"checked\""));
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent(null);
    // /pages/chickenBatch/../commons/taglib.jsp(8,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("selected");
    // /pages/chickenBatch/../commons/taglib.jsp(8,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue(new String("selected=\"selected\""));
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }
}
