<template>
  <div class="list-group">
      <a v-for="board in data.boardList" v-bind:key="board.boardSeq" class="list-group-item list-group-item-action active" aria-current="true">
        <div class="d-flex w-100 justify-content-between">
          <h5 class="mb-1">{{board.boardType.label}}</h5>
          <small>{{board.regDt}}</small>
        </div>
        <p class="mb-1">{{board.title}}</p>
        <small>{{board.contents}}</small>
      </a>
      <a v-if="data.boardList.length == 0" class="list-group-item list-group-item-action active" aria-current="true">
        게시물이 없습니다.
      </a>
    </div>
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
