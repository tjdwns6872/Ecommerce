import axios from 'axios';

const apiRequest = async (method, url, params = {}, data = {}) => {
  try {
    var token = "Bearer "+localStorage.getItem('accessToken');
    const response = await axios({
      method,
      url,
      params,
      data,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': token,
      },
    });
    return response.data;
  } catch (error) {
    console.error(`API 호출 실패: ${error}`);
    throw error;
  }
};

export default apiRequest;
