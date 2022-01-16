<template>
  <router-view></router-view>
</template>

<script>
import { onMounted, reactive } from 'vue'

export default {
  name: 'App',
  components: {
  },
  setup() {
    const data = reactive({
      boardList: []
    });
    const getList = () => {
      fetch('http://localhost:9000/faq')
      .then(response => response.json())
      .then(response => {
      data.boardList = response.data;
      });
    };
    onMounted(() => {
      getList();
    });
    return {
      data: data,
      getList: getList
    }
  }
}
</script>

<style>
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css'
</style>
