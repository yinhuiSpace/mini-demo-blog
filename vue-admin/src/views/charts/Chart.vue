<script setup lang="ts">
import * as echarts from 'echarts';
import {onMounted, ref} from "vue";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";

const blogData = ref([])

const titleList = ref([])

const viewCountList = ref([])

const getData = () => {
  axiosInstance.get("/blog-service/blog/data")
      .then((resp) => {
        if (resp.data.isSuccess) {
          blogData.value = resp.data.content

          pieOption.value.series[0].data = blogData.value.map(v => {
            return {
              value: v.viewCount,
              name: v.title
            }
          })

          const pie = document.getElementById("pie");
          const pieCharts = echarts.init(pie);
          pieCharts.setOption(pieOption.value)
        } else {
          ElMessage.error(resp.data.message)
        }
      })
}

onMounted(() => {
  getData()
})

const option = {
  title: {
    text: "博客访问量"
  },
  tooltip: {
    trigger: "axis"
  },
  xAxis: {
    type: 'category',
    data: ['星期一', '星期二', '星期三', '星期一', '星期一', '星期一', '星期一']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [
        100,
        93,
        901,
        934,
        1290,
        133,
        132
      ],
      type: 'line',
      smooth: true,
      name: "1"
    },
    {
      data: [
        1500,
        932,
        901,
        934,
        1290,
        133,
        1320
      ],
      type: 'line',
      smooth: true,
      name: "2"
    }
  ]
};

const barOption = ref({
  title: {
    text: "博客访问量"
  },
  tooltip: {
    trigger: "axis"
  },
  xAxis: {
    type: 'category',
    data: []
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [],
      type: 'bar',
      smooth: true,
      name: "1"
    }
  ]
})

const pieOption = ref({
  title: {
    text: "博客种类构成统计",
    left: "center"
  },
  legend: {
    orient: "vertical",
    left: "left"
  },
  tooltip: {
    trigger: "item"
  },
  toolbox: {
    show: true,
    feature: {
      mark: {show: true},
      dataView: {show: true, readOnly: false},
      restore: {show: true},
      saveAsImage: {show: true}
    }
  },
  series: [
    {
      name: 'Nightingale Chart',
      type: 'pie',
      radius: "70%",
      center: ['60%', '60%'],
      roseType: 'area',
      itemStyle: {
        borderRadius: 8
      },
      data: []
    }
  ]
});
</script>

<template>
  <div>
<!--    {{ blogData }}-->
<!--    <el-row :gutter="10">-->
<!--      <el-col :span="12">-->
<!--        <el-card>-->
<!--          <div id="line" style="width: 100%;height: 400px">-->

<!--          </div>-->
<!--        </el-card>-->
<!--      </el-col>-->

<!--      <el-col :span="12">-->
<!--        <el-card>-->
<!--          <div id="bar" style="width: 100%;height: 400px;"/>-->
<!--        </el-card>-->
<!--      </el-col>-->
<!--    </el-row>-->

    <el-card>
      <div id="pie" style="width: 100%;height: 800px"/>
    </el-card>
  </div>
</template>

<style scoped lang="less">

</style>