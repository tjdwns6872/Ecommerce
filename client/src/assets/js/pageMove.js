const pageMove = {
    paramUrl: (url, param) =>{
        window.location.href=url+param;
    },
    paramsUrl: (url, params) => {
        var i = 0;
        var size = Object.keys(params).length;
        for(var key in params) {
            console.log();
            url = url+key+"="+params[key];
            if(i !== (size-1)){
                url += "&";
            }
            i++;
        }
        window.location.href=url;
    },
    paramsToJson: (url) => {
        var urlObject = new URL(url);
        var params = new URLSearchParams(urlObject.search);
        var result = {};
        
        for (var [key, value] of params) {
            if (result.hasOwnProperty(key)) {
            if (Array.isArray(result[key])) {
                result[key].push(value);
            } else {
                result[key] = [result[key], value];
            }
            } else {
            result[key] = value;
            }
        }
        return result;
    }
}

export default pageMove;