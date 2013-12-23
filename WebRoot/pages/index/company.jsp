<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/index/index.css" type="text/css" rel="stylesheet"></link>
<link href="${ctx }/css/index/skdslider.css" type="text/css" rel="stylesheet">
<title>仁安主页</title>
<style type="text/css">
	.textContent{
		font-size: 14px;margin-left:14px;margin-right:14px;margin-top:14px;margin-bottom:32px;line-height: 200%;margin-top: 12px;margin-bottom: 24px;text-indent: 28px;
	}
	.textContent span{
		font-size: 16px;font-weight: bold;color: #235e06;
	}
	.companyBanner{
		height: 215px;background-image: url("${ctx}/images/index/company/company.jpg");
	}
	.conTitle{
		margin-left:14px;margin-right:14px;border-bottom:1px black dotted;
	}
	.title{
		font-size: 28px;font-weight: bold;color: #1b4d04;margin-bottom: 12px;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="banner companyBanner">
	</div>
	<div class="contWith " >
		<div class="conTitle">
			<div class="title">人安简介</div>
		</div>
		<div class="textContent">
		<p><span>资兴市人安生态禽业有限公司</span>，是一家生态禽业养殖企业；企业主要从事名贵禽类的繁育、养殖、销售及技术服务。公司立志于建立起一套可追朔的安
		全质量体系，从根本上确保鸡肉产品的安全，让老百姓吃上放心鸡肉。</p>
			<p><span>精选的品种</span>，公司选育国内珍贵的肉蛋兼用的珍惜禽种—雪峰乌骨鸡。雪峰乌骨鸡被列入国家禽类遗传资源名录，具“益气补虚，滋阴养肾”等功效，更有“
		药鸡”之称。</p>
			<p><span>宜人的生长环境</span>， 公司养殖基地坐落于国家AAAA级风景区—东江湖湿地公园园区内；具有迷人的原生态环境和国家地表一类水源，可直接供人饮用的水
		源保证了禽类养殖环境的安全无公害。</p>
			<p><span>纯天然放养模式</span>， 人安的每一只鸡都采用纯天然自由放养的模式饲喂，不限制鸡的活动自由，遵循及的自然生长规律，保证150天以上的养殖周期，让肉感
		更鲜美，肉质更安全，营养价值更全面。</p>
			<p><span>安全可追溯</span>， 人安的每一只鸡都可以追踪溯源，可以监督鸡成长的每一个环节中的饲喂，用药情况，从而从根本上确保鸡的安全，可靠，让老百姓享用安全、
		营养、放心的禽肉“引山涧清泉，养生态好鸡”：我们始终相信，具有迷人的原生态环境和国家地表一类水源，会让员工的心情变的愉悦，让鸡肉的品质更加绿色生
		态。</p>
	</div>
	</div>
	
<jsp:include page="bottom.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${ctx }/widgets/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/skdslider.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		$('#coin-slider').skdslider({'delay':5000, 'fadeSpeed': 2000,'autoStart':true});
	});
    
</script>
</html>