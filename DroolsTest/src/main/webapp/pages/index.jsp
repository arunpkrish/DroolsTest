<html>



<form action="executeRule" modelAttribute="rule" method="POST">
<input type="text" name="param1" value="${rule.param1}"/> <br/>
<input type="text" name="param2" value="${rule.param2}"/> <br/>
<input type="text" name="param3" value="${rule.param3}"/> <br/>
<input type="submit"/>

<h1>Result : ${rule.result}</h1>
</form>
</html>