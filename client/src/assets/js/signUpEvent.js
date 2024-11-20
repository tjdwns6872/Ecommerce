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
    },
    createUser: async () => {
        var form = document.getElementById('signup-form').elements;
        var post = {}
        console.log(form)
        for (const item of form) {
            switch (item.localName) {
                case 'input':
                    post[item.id] = item.value;
                    break;
                default:
                    break;
            }
        }
        console.log(post);
        // var data = await ApiFactory.put('http://localhost:8081/ecommerce/api/user/join', {});
        // console.log(data);
    }
}

export default signUpEvent;