import ApiFactory from '../../services/ApiFactory';
const signUpEvent = {
    authCodeSend: async () => {
        try{
            var phone = document.getElementById("user-phone").value;
            var data = await ApiFactory.put('http://localhost:8081/ecommerce/api/user/join/CertCode', {'phone':phone});
            console.log(data);
        }catch(error){
            console.error(error)
        }
    }
}

export default signUpEvent;