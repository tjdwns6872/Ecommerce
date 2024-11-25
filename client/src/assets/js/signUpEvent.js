import ApiFactory from '../../services/ApiFactory';
const signUpEvent = {
    authCodeSend: async () => {
        try{
            var phone = document.getElementById("user-phone").value;
            var data = await ApiFactory.put('http://localhost:8081/ecommerce/api/user/join/CertCode', {'phone':phone});
            return data;
        }catch(error){
            console.error(error)
        }
    },
    codeCheck: async () => {
        var certId = document.getElementById('user-certId').value;
        var code = document.getElementById('user-code').value;
        var params = {"certId":certId, "code":code}
        var data = await ApiFactory.get("http://localhost:8081/ecommerce/api/sms/cert/code/join", params);
        return data;
    },
    createUser: async () => {
        var form = document.getElementById('signup-form').elements;
        var params = {}
        console.log(form)
        for (const item of form) {
            switch (item.localName) {
                case 'input':
                    params[item.id] = item.value;
                    break;
                default:
                    break;
            }
        }
        console.log(params);
        var data = await ApiFactory.put("http://localhost:8081/ecommerce/api/user/join", params);
        console.log(data);
    }
}

export default signUpEvent;