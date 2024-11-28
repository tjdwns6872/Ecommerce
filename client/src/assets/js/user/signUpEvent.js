import ApiFactory from '../../../services/ApiFactory';

const signUpEvent = {
    authCodeSend: async () => {
        try{
            var phone = document.getElementById("userPhone").value;
            var data = await ApiFactory.put('http://localhost:8081/ecommerce/api/user/join/CertCode', {'phone':phone});
            return data;
        }catch(error){
            console.error(error)
        }
    },
    codeCheck: async () => {
        var certId = document.getElementById('userCertId').value;
        var code = document.getElementById('userCode').value;
        var params = {"certId":certId, "code":code}
        var data = await ApiFactory.get("http://localhost:8081/ecommerce/api/sms/cert/code/join", params);
        return data;
    },
    // settingData: (state) =>{
    //     for(const key in state) {
    //         var object = document.getElementById(key);
    //         console.log(object);
    //     }
    // },
    createUser: async () => {
        var form = document.getElementById('signup-form').elements;
        var params = {}
        console.log(form)
        for (var item of form) {
            switch (item.localName) {
                case 'input':
                    params[item.id] = item.value;
                    break;
                default:
                    break;
            }
        }
        var data = await ApiFactory.put("http://localhost:8081/ecommerce/api/user/join", params);
        console.log(data);
    }
}

export default signUpEvent;