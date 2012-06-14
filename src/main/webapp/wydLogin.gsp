<div class="container">

<form class="form-horizontal" method="post" action="acmAuthenticate.groovy">
<fieldset>
	<legend>Sign In</legend>
	<div class="control-group">
       <label class="control-label" for="userId">User Id</label>
		<div class="controls">
			<input type="text" class="input-medium" id="userId" name="userId">
		</div>
	</div>
	<div class="control-group">
       <label class="control-label" for="password">Password</label>
		<div class="controls">
			<input type="password" class="input-medium" id="password" name="password">
		</div>
	</div>
	<div class="form-actions">
		<button type="submit" class="btn btn-primary">Sign In</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="reset" class="btn">Reset</button>
    </div>
</fieldset>	
</form>

</div>

<script type="text/javascript">
	\$(document).ready(function() {
	 	\$('#userId').focus();
	});
</script>
