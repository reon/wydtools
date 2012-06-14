<div class="container">

<p align="right" class="pull-right">
	<a class="btn btn-medium" href="acmUserCreateOrEdit.groovy">
		<i class="icon-plus"></i>
		Add&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</a>
</p>

</div>

<div class="container">

<form method="post" action="acmUserDisable.groovy">

<table class="table table-striped table-condensed table-bordered">
<thead>
<tr>
    <th>#</th>
    <th>User Id</th>
    <th>Name</th>
    <th>Email Id</th>
    <th>Time Zone</th>
    <th>Created Time</th>
    <th>Updated Time</th>
    <th>&nbsp;</th>
</tr>
</thead>
<tbody>
<%
	int index = 0
	request.users.each { user ->
		index++
%>
<tr>
	<td>$index</td>
    <td><a href="acmUserCreateOrEdit.groovy?id=${user.id}">$user.id</a></td>
    <td>$user.name</td>
    <td>$user.emailId</td>
    <td>$user.timeZone</td>
    <td>$user.createdTime</td>
    <td>$user.updatedTime</td>
    <td>
    	<a href="acmUserDisable.groovy?&id=${user.id}">
    		<i class="icon-remove"></i>
    	</a>
    	&nbsp;
    	<input type="checkbox" name="id" value="${user.id}">
    </td>
</tr>
<%
	}
%>
</tbody>
</table>

<p align="right">
	<button class="btn btn-medium" type="submit" name="action" value="disable">
		<i class="icon-remove"></i>&nbsp;Disable
	</button>
</p>
	
</form>


</div>