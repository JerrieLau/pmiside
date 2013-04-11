/**
 * 整除
 * @param exp1
 * @param exp2
 * @returns {Number}
 */
function div(exp1, exp2) {  
    var n1 = Math.round(exp1);  
    var n2 = Math.round(exp2);  
    var rslt = n1/n2;  
    if (rslt >= 0) {  
        rslt = Math.floor(rslt); //返回小于等于原rslt的最大整数。  
    }else{  
        rslt = Math.ceil(rslt); //返回大于等于原rslt的最小整数。  
    }  
    return rslt;  
} 

/**
 * 判定日期是否有效
 * @param value
 * @returns {Boolean}
 */
function isValidateDate(value){ 
	var ereg = /^(\d{1,4})(-|\/)(\d{1,2})$/; 
	var r = value.match(ereg);  
	if (r == null) {        
		return false;   
	}   
	var d = new Date(r[1], r[3] - 1); 
	var result = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3]);    
	return result;
}