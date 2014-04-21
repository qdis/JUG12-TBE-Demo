
var validation = {
		
		encryptPassword : function(password){
			hash = CryptoJS.SHA1(password);
			return hash;
		},

		validatePasswordMatch : function(p1,p2){
			if(p1 == p2){
				return true;
			}else{
				return false;
			}
		},
		
		addError : function(error){
			errorList = document.getElementById('errorList');
			div = errorList.parentNode;
			console.log(div);
			div.style.display='block';
			var li=document.createElement('li');
			li.innerHTML = error;
			errorList.appendChild(li);
		},
		
		clearMessage : function(){
			m = document.getElementById('value');
			console.log(m);
			console.log(m.value);
			m.value = '';
			return true;
		},
		
		login : function(){
			p = document.getElementById('password');
			p.value = validation.encryptPassword(p.value);
			return true;
		},
		
		isFormValid : function(){
			
			valid = true;
			
			u = document.getElementById('username');
			
			p1 = document.getElementById('password');
			p2 = document.getElementById('passwordRepeat');
			
			if(u.value.length==0){
				validation.addError('Username is required!');
				valid = false;
			}
			
			if(p1.value.length == 0 || p2.value.length ==0){
				validation.addError('Password is required!');
				valid = false;
			}
					
			if(validation.validatePasswordMatch(p1.value,p2.value)){
				p1.value = validation.encryptPassword(p1.value);
			}else{
				validation.addError('Passwords do not match!');
				valid = false;
			}
			return valid;
		}
}