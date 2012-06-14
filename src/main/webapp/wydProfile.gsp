<% 
	def user = session.user
	if(user.timeZone == null) {
		user.timeZone = 'Chennai'
	}
%>

<div class="container">

<ul id="wydProfileTab" class="nav nav-tabs">
	<li class="active"><a href="#account" data-toggle="tab">Account</a>	</li> 
	<li><a href="#password" data-toggle="tab">Password</a></li>
	<li><a href="#settings" data-toggle="tab">Settings</a></li> 
</ul>
<div id="wydProfileTabContent" class="tab-content">
	
	<div class="tab-pane fade in active" id="account">
		<div name="accountForm" class="form-horizontal">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="userId">User Id</label>
			  	<div class="controls">
					<input type="text" class="input-medium" name="userId" value="${user.id}" id="userId" disabled>
			  	</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">Name</label>
			  	<div class="controls">
					<input type="text" class="input-xlarge" name="name" value="${user.name}" id="name">
			  	</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="emailId">Email Id</label>
			  	<div class="controls">
					<input type="text" class="input-xlarge" name="emailId" value="${user.emailId}" id="emailId" disabled>
			  	</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="timeZone">Time Zone</label>
				<div class="controls">
					<select name="timeZone" id="timeZone" >
					<%
						def timeZones = application.globalTimeZones
						timeZones.each { key, val ->
							if(key == user.timeZone) {
					%>
						<option value="${key}" selected="selected">${val}</option>
					<%		} else {	%>
						<option value="${key}">${val}</option>
					<%	
							}
						}
					%>	
					</select>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-primary" onClick="wydProfileUpdateAccount()">Save</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn">Reset</button>
			</div>
		</fieldset>
		</div>
	</div>

	<div class="tab-pane fade" id="password">
		<div name="passwordForm" class="form-horizontal">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="currentPassword">Current Password</label>
			  	<div class="controls">
					<input type="password" class="input-medium" id="currentPassword">
			  	</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="newPassword">New Password</label>
			  	<div class="controls">
					<input type="password" class="input-xlarge" id="newPassword">
			  	</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="retypeNewPassword">Retype New Password</label>
			  	<div class="controls">
					<input type="password" class="input-xlarge" id="retypeNewPassword">
			  	</div>
			</div>
			<div class="form-actions">
				<button type1="submit" class="btn btn-primary" onClick="wydProfileUpdatePassword()">Save</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="btn"">Reset</button>
			</div>
		</fieldset>
		</div>
	</div>

	<div class="tab-pane fade" id="settings">
    	<p>Coming soon...</p>
	</div>
	
</div>

</div>

<script type="text/javascript">
\$(document).ready(function() {
 	\$('#userId').focus();
 	
 	\$('#retypeNewPassword').bind('keydown', function(e) {
    	var code = (e.keyCode ? e.keyCode : e.which);
    	 if(code == 13) {	wydProfileUpdatePassword();	}
    });
});

function wydProfileUpdateAccount() {
	var name = \$('#name').val();
	var timeZone = \$('#timeZone').val();
	\$.post('wydProfileUpdateAccount.groovy',
			{	
				name     : name, 
				timeZone : timeZone 
			},
			function(data) {
				//alert(data);
				var msg = \$.parseJSON(data);
				var noty_id = noty({
					text   : msg.value,
					type   : msg.notyType,
					layout : 'top',
					theme  : 'noty_theme_twitter'
				});
				\$('#name').focus();
		 	}
		);
};

function wydProfileUpdatePassword() {
	var currentPassword = \$('#currentPassword').val();
	var newPassword = \$('#newPassword').val();
	var retypeNewPassword = \$('#retypeNewPassword').val();

	\$.post('wydProfileUpdatePassword.groovy',
			{	
				currentPassword   : currentPassword, 
				newPassword       : newPassword,
				retypeNewPassword : retypeNewPassword 
			},
			function(data) {
				//alert(data);
				var msg = \$.parseJSON(data);
				var noty_id = noty({
					text   : msg.value,
					type   : msg.notyType,
					layout : 'top',
					theme  : 'noty_theme_twitter'
				});
				\$('#currentPassword').focus();
				\$('#currentPassword').val('');
				\$('#newPassword').val('');
				\$('#retypeNewPassword').val('');
		 	}
		);
};
</script>
	
