<template>
  <div class="container">
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input type="text" v-model="data.board.title" class="form-control" id="title" placeholder="입력해주세요.">
    </div>
    <div class="mb-3">
      <label for="contents" class="form-label">내용</label>
      <textarea name="contents" v-model="data.board.contents" class="form-control" id="contents" rows="3"></textarea>
    </div>
    <div class="mb-3">
      <label for="uploadFile" class="form-label">파일첨부</label>
      <input name="uploadFile" v-on:change="changeFile" class="form-control" type="file" id="uploadFile">
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-primary me-md-2" type="button">취소</button>
      <button class="btn btn-primary" type="button" v-on:click="save">저장</button>
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
      board: {
        boardType: 'FAQ',
        title: null,
        contents: null,
        uploadFile: null
      }
    });
    const save = () => {
      var formData = new FormData();
      formData.append('boardType', data.board.boardType);
      formData.append('title', data.board.title);
      formData.append('contents', data.board.contents);
      if(data.board.uploadFile != null){ //파일 첨부하는 경우에만 formData 처리
        formData.append('uploadFile', data.board.uploadFile);
      }
      fetch('http://localhost:9000/faq/save',{
        method : 'PUT',
        body: formData
      })
      .then(response => response.json())
      .then(response => {
      data.boardList = response.data;
      });
    };
    const changeFile = (e) => {
      data.board.uploadFile = e.target.files[0];
      console.log(data.board.uploadFile);
    };
    onMounted(() => {
    });
    return {
      data: data,
      changeFile: changeFile,
      save :save,
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  @import 'https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css'
</style>