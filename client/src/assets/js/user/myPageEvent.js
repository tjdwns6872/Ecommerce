import ApiFactory from '../../../services/ApiFactory';

const myPageEvent = {
    userData: async () => {
        var data = await ApiFactory.get("http://localhost:8081/ecommerce/api/user/detail", 4);
        return data;
    },
    logout: () => {
        localStorage.removeItem('accessToken');
        window.location.href="/login";
    }
}

export default myPageEvent;