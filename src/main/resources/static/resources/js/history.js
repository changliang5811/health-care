
$(function(){
    var myCharts = echarts.init(document.getElementById("history1"));
    myCharts.showLoading();    //数据加载完之前先显示一段简单的loading动画
    option = {
        title: {
            text: '过去一周训练历史记录',
            subtext: '单位：组'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['已完成', '未完成']
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ["1","2","3","4","5","6","7"]
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '已完成',
                type: 'bar',
                data: [4,5,7,11,8,10,15],
            },
            {
                name: '未完成',
                type: 'bar',
                data: [12,11,13,7,10,7,1],
            }
        ]
    };
    myCharts.hideLoading();    //隐藏加载动画
    myCharts.setOption(option);
});
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
                min: 0,
                max: 10,
                interval: 2,
                axisLabel: {
                    formatter: '{value} 组'
                }
            },
        ],
        series: [
            {
                name: '已完成',
                type: 'bar',
                data: [1,2,3,4,5]
            },
            {
                name: '未完成',
                type: 'bar',
                data: [3,4,6,7,8]
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
    var end_time = document.getElementById("end_time").value;

    var dates=[];    //日期（实际用来盛放X轴坐标值）
    var nums1=[];    //已完成数组（实际用来盛放Y坐标值）
    var nums2=[];    //未完成数组（实际用来盛放Y坐标值）

    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/healthCare/itemManage/selectHistory",    //请求发送到xx处
        data : {"user_account":user_account,"start_time":start_time,"end_time":end_time},
        dataType : "json",        //返回数据形式为json
        success : function(data) {
            //请求成功时执行该函数内容，data即为服务器返回的json对象
            if (data) {
                $.each(data.listHistory,function(key,value){
                    dates.push(value.update_time);
                });
                $.each(data.listHistory,function(key,value){
                    nums1.push(value.trainInfo.length);
                });
                $.each(data.listHistory,function(key,value){
                    nums2.push(value.day_num*value.group_num-value.trainInfo.size);
                });
                myCharts.hideLoading();    //隐藏加载动画
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
                });

            }

        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myCharts.hideLoading();
        }
    })
}

