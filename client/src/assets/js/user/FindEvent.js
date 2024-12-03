import ApiFactory from '../../../services/ApiFactory';

const findEvent = {
    findCodeSend: async (type) => {
        try{
            var phone = document.getElementById("userPhone").value;
            var name = document.getElementById("userName").value;
            var params = {"phone":phone, "name":name}
            if(type==="passwordFind"){
                var email = document.getElementById("userEmail").value;
                params['email'] = email;
            }
            var data = await ApiFactory.put('http://localhost:8081/ecommerce/api/user/'+type+'/CertCode', params);
            return data;
        }catch(error){
            console.error(error)
        }
    },
    codeCheck: async () => {
        var certId = document.getElementById('userCertId').value;
        var code = document.getElementById('userCode').value;
        var params = {"certId":certId, "code":code}
        var data = await ApiFactory.get("http://localhost:8081/ecommerce/api/sms/cert/code/find", params);
        return data;
    },
}

export default findEvent;