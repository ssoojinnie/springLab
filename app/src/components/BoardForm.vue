<template>
  <div class="container">
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input type="email" class="form-control" id="title" placeholder="입력해주세요.">
    </div>
    <div class="mb-3">
      <label for="contents" class="form-label">내용</label>
      <textarea class="form-control" id="contents" rows="3"></textarea>
    </div>
    <div class="mb-3">
      <label for="uploadFile" class="form-label">파일첨부</label>
      <input name="uploadFile" class="form-control" type="file" id="uploadFile">
    </div>
  </div>
</template>

<script>
import { onMounted, reactive } from 'vue'

export default {
  name: 'BoardForm',
  components: {
  },
  setup() {
    const data = reactive({
      boardList: []
    });
    const getList = () => {
      fetch('http://localhost:9000/faq/save')
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
