<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/19 0019
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--todo导入jQuery的jar包-->
</head>
<body>
<script>
    $("#img").click(function () {
        $(this).attr("src", "yzm.action?" + new Date().getTime());
    });
</script>
<td><img id="img" src="yzm.action"/>${validateCode}</td>
<td><input type="text" name="yzma"/><br/></td>

</body>
</html>
