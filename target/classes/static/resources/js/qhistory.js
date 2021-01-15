var myCharts = echarts.init(document.getElementById("history1"));
//日期增减天数函数
function addDate(date,days){
    var d=new Date(date);
    d.setDate(d.getDate()+days);
    var m=d.getMonth()+1;
    return d.getFullYear()+'-'+m+'-'+d.getDate();
}
//获取当前时间
var date = new Date();
var seperator1 = "-";
var year = date.getFullYear();
var month = date.getMonth() + 1;
var strDate = date.getDate();
if (month >= 1 && month <= 9) {
    month = "0" + month;
}
if (strDate >= 0 && strDate <= 9) {
    strDate = "0" + strDate;
}
// var minute=date.getMinutes()<=9?"0"+date.getMinutes():date.getMinutes();
// var initEndTime = year + seperator1 + month + seperator1 + strDate+" "+date.getHours()+":"+minute+":"+date.getSeconds();
// var lastHour = (date.getHours()-1)<0?0:(date.getHours()-1);//判断0点时结果-1异常
// var initStartTime = year + seperator1 + month + seperator1 + strDate+" "+lastHour+":"+minute+":"+date.getSeconds();
var initStartTime = year + seperator1 + month + seperator1 + strDate;
$("#start_time").val(initStartTime);


function initEcharts() {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data: ['已完成', '未完成']
        },
        xAxis: [
            {
                type: 'category',
                data: [],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '个数',
                // min: 0,
                // max: 10,
                interval: 1,
                axisLabel: {
                    formatter: '{value} 组'
                }
            },
        ],
        series: [
            {
                name: '已完成',
                type: 'bar',
                data: []
            },
            {
                name: '未完成',
                type: 'bar',
                data: []
            },
        ]
    }
    myCharts.setOption(option);
}

function query(){
    initEcharts();
    myCharts.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var user_account = document.getElementById("user_account").value;
    var start_time = document.getElementById("start_time").value;
    var end_time = addDate(start_time,1);
   // var end_time = document.getElementById("end_time").value;

    var dates=[];    //日期（实际用来盛放X轴坐标值）
    var nums1=[];    //已完成数组（实际用来盛放Y坐标值）
    var nums2=[];    //未完成数组（实际用来盛放Y坐标值）

    var sum1 = 0;    //一天完成的组数和
    var sum2 = 0;    //一天未完成的组数和
var x = 0;
    for(var i=1;i<=7;i++){
        x=x+1;
        $.ajax({
            type : "get",
            async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "/healthCare/itemManage/selectHistory",    //请求发送到xx处
            data : {"user_account":user_account,"start_time":start_time,"end_time":end_time},
            dataType : "json",        //返回数据形式为json
            success : function(data) {
                //请求成功时执行该函数内容，data即为服务器返回的json对象=>data.listhistory是一整天的trainplan组成的list
                if (data) {
                    $.each(data.listHistory,function(index,item){
                        //console.log(x);
                        sum1 = item.trainInfo.length;//alert(value.trainInfo.length);
                        sum2 = item.day_num*item.group_num-sum1;//alert(item.group_num);
                        //   dates.push(value.update_time);
                        //   nums1.push(value.trainInfo.length);
                        //   nums2.push(value.day_num*value.group_num-value.trainInfo.size);
                    });
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(sum1);
                    nums2.push(sum2);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
                else{//此日期无训练计划时
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(0);
                    nums2.push(0);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
            },
            error : function(errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myCharts.hideLoading();
            }
        })

        //start_time = addDate(start_time,1);
        //end_time = addDate(end_time,1);
    }


   //****

}

function  query_fast_lastweek() {
    initEcharts();
    myCharts.showLoading();    //数据加载完之前先显示一段简单的loading动画
    var user_account = document.getElementById("user_account").value;
    var start_time = addDate(document.getElementById("start_time").value,-7);
    var end_time = addDate(start_time,1);

    var dates=[];    //日期（实际用来盛放X轴坐标值）
    var nums1=[];    //已完成数组（实际用来盛放Y坐标值）
    var nums2=[];    //未完成数组（实际用来盛放Y坐标值）

    var sum1 = 0;    //一天完成的组数和
    var sum2 = 0;    //一天未完成的组数和
    for(var i=1;i<=7;i++){
        //x=x+1;
        $.ajax({
            type : "get",
            async : false,            //这里只能用同步请求否则for与ajax会分开运行（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "/healthCare/itemManage/queryFastTrain",    //请求发送到xx处
            data : {"user_account":user_account,"start_time":start_time,"end_time":end_time},
            dataType : "json",        //返回数据形式为json
            success : function(data) {
                //请求成功时执行该函数内容，data即为服务器返回的json对象=>data.listhistory是一整天的trainplan组成的list
                if (data) {
                    $.each(data.listHistory,function(index,item){
                        //console.log(x);
                        sum1 = item.trainInfo.length;
                        sum2 = item.day_num*item.group_num-sum1;
                    });
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);
                    nums1.push(sum1);
                    nums2.push(sum2);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
                else{//此日期无训练计划时
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(0);
                    nums2.push(0);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
            },
            error : function(errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myCharts.hideLoading();
            }
        })

    }
    $("#start_time").val(addDate(start_time,-7));
}

function  query_fast_nextweek() {
    initEcharts();
    myCharts.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var user_account = document.getElementById("user_account").value;
    var start_time = addDate(document.getElementById("start_time").value,7);
    console.log(start_time);
    var end_time = addDate(start_time,1);
    // var end_time = document.getElementById("end_time").value;

    var dates=[];    //日期（实际用来盛放X轴坐标值）
    var nums1=[];    //已完成数组（实际用来盛放Y坐标值）
    var nums2=[];    //未完成数组（实际用来盛放Y坐标值）

    var sum1 = 0;    //一天完成的组数和
    var sum2 = 0;    //一天未完成的组数和
    for(var i=1;i<=7;i++){
        $.ajax({
            type : "get",
            async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "/healthCare/itemManage/queryFastTrain",    //请求发送到xx处
            data : {"user_account":user_account,"start_time":start_time,"end_time":end_time},
            dataType : "json",        //返回数据形式为json
            success : function(data) {
                //请求成功时执行该函数内容，data即为服务器返回的json对象=>data.listhistory是一整天的trainplan组成的list
                if (data) {
                    $.each(data.listHistory,function(index,item){
                        sum1 = item.trainInfo.length;
                        sum2 = item.day_num*item.group_num-sum1;
                    });
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(sum1);
                    nums2.push(sum2);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
                else{//此日期无训练计划时
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(0);
                    nums2.push(0);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
            },
            error : function(errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myCharts.hideLoading();
            }
        })

    }
    //$("#start_time").val(start_time);
    $("#start_time").val(addDate(start_time,-7));
}

function query_fast(){
    initEcharts();
    myCharts.showLoading();    //数据加载完之前先显示一段简单的loading动画

    var user_account = document.getElementById("user_account").value;
    var start_time = document.getElementById("start_time").value;
    var end_time = addDate(start_time,1);
    // var end_time = document.getElementById("end_time").value;

    var dates=[];    //日期（实际用来盛放X轴坐标值）
    var nums1=[];    //已完成数组（实际用来盛放Y坐标值）
    var nums2=[];    //未完成数组（实际用来盛放Y坐标值）

    var sum1 = 0;    //一天完成的组数和
    var sum2 = 0;    //一天未完成的组数和
    var x = 0;
    for(var i=1;i<=7;i++){
        x=x+1;
        $.ajax({
            type : "get",
            async : false,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url : "/healthCare/itemManage/queryFastTrain",    //请求发送到xx处
            data : {"user_account":user_account,"start_time":start_time,"end_time":end_time},
            dataType : "json",        //返回数据形式为json
            success : function(data) {
                //请求成功时执行该函数内容，data即为服务器返回的json对象=>data.listhistory是一整天的trainplan组成的list
                if (data) {
                    $.each(data.listHistory,function(index,item){
                        console.log(x);
                        sum1 = item.trainInfo.length;
                        sum2 = item.day_num*item.group_num-sum1;
                    });
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(sum1);
                    nums2.push(sum2);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
                else{//此日期无训练计划时
                    dates.push(start_time);
                    start_time = addDate(start_time,1);
                    end_time = addDate(end_time,1);//alert(start_time);
                    nums1.push(0);
                    nums2.push(0);
                    //////////////////////
                    myCharts.hideLoading(),    //隐藏加载动画
                        myCharts.setOption({        //加载数据图表
                            xAxis: {
                                data: dates
                            },
                            series: [
                                {
                                    // 根据名字对应到相应的系列
                                    name: '已完成',
                                    data: nums1
                                },
                                {
                                    // 根据名字对应到相应的系列
                                    name: '未完成',
                                    data: nums2
                                }
                            ]
                        })
                }
            },
            error : function(errorMsg) {
                //请求失败时执行该函数
                alert("图表请求数据失败!");
                myCharts.hideLoading();
            }
        })

        //start_time = addDate(start_time,1);
        //end_time = addDate(end_time,1);
    }


    //****

}

