<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${ctx }/css/common_pagination.css" type="text/css" media="screen" />
<c:if test="${not empty page && not empty page.result}">
<table id="paginationTableId" class="pagebar"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="right" width="520">
    总数${page.totalCount}
    每页显示<select name="pageSize" align="absmiddle" onchange="cwSearchPagingOrder('${page.currentPageNo}',$('#pageSize option:selected')[0].value);" id="pageSize" style="width: 50px">
      <c:forEach var="i" begin="5" end="50" step="5">
          <option value="${i}" <c:if test="${i== page.pageSize}">selected</c:if>>${i}</option>
      </c:forEach>
      </select>
    <c:choose><c:when test="${page.currentPageNo==1}">
       <span class="pre2">上一页</span>&nbsp;
    </c:when>
    <c:otherwise>
       <a href="#" class="pre" onClick="javascript:cwSearchPagingOrder(${page.currentPageNo-1},'${page.pageSize}');return false;">上一页</a>&nbsp;
    </c:otherwise></c:choose>
	<c:if test="${page.currentPageNo>5}"><a href="#" onClick="javascript:cwSearchPagingOrder(1,'${page.pageSize}');return false;">1</a>...</c:if>
    <c:set var="minNum" value="${page.currentPageNo-2}"/>
    <c:set var="maxNum" value="${page.currentPageNo+2}"/>
    <c:if test="${page.currentPageNo<=5}"><c:set var="minNum" value="1"/></c:if>
    <c:if test="${page.currentPageNo>=page.totalPageCount-5}"><c:set var="maxNum" value="${page.totalPageCount}"/></c:if>
    <c:if test="${page.totalPageCount>=7 && maxNum<7}"><c:set var="maxNum" value="7"/></c:if>
    <c:forEach var="i" begin="${minNum}" end="${maxNum}" step="1">
      <c:choose><c:when test="${i==page.currentPageNo}"><span class="current">${i}</span></c:when><c:otherwise><a href="#" onClick="javascript:cwSearchPagingOrder(${i},'${page.pageSize}');return false;">  ${i}</a></c:otherwise></c:choose>
    </c:forEach>
    <c:if test="${page.totalPageCount>maxNum}">...<a href="#" onClick="javascript:cwSearchPagingOrder(${page.totalPageCount},'${page.pageSize}');return false;">${page.totalPageCount}</a></c:if>
     <c:choose><c:when test="${page.currentPageNo==page.totalPageCount}">
        <span class="next2">下一页</span>
     </c:when>
    <c:otherwise>
       <a href="#" class="next" onClick="javascript:cwSearchPagingOrder(${page.currentPageNo+1},'${page.pageSize}');return false;">下一页</a>&nbsp;
    </c:otherwise>
    </c:choose>
     第<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'');document.all.currentPage.value=this.value;" onafterpaste="this.value=this.value.replace(/\D/g,'');"  maxlength="5" name="curPage" id="curPage"  value="" size="3" style="width:27px"/>页
     <input type="button" name="curPagebutton" value="go" onclick="cwSearchPagingOrder(getValue(),'${page.pageSize}');" />
    </td>
  </tr>
</table>
</c:if>
<script type="text/javascript">
function getValue(){
	var curPage=$("#curPage").val();
	return curPage;
}
function cwSearchPagingOrder(curPage,pageSize){
	var qForm=$("#searchPageForm");
    if(typeof(qForm)=="undefined") return;
    if(typeof(curPage)!="undefined" && curPage!=null && curPage!=""){
      $("#currentPage").val(curPage);
    }
    if(typeof(pageSize)!="undefined" && pageSize!=null && pageSize!=""){
      $("#paramPageSize").val(pageSize);
    }
    qForm.submit();
}
</script>