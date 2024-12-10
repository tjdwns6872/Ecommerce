import ApiFactory from '../../../services/ApiFactory';

const myPageEvent = {
    userData: async () => {
        var data = await ApiFactory.get("http://localhost:8081/ecommerce/api/user/detail", 4);
        console.log(data);
        return data;
    }
}

export default myPageEvent;