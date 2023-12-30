<template>
  {{ data }}
</template>

<script>

export default {

  mounted() {
    this.fnGetList()
  },
  methods: {
    fnGetList() {
      this.requestBody = { // 데이터 전송
        keyword: this.keyword
      }

      this.$axios.get(this.$serverUrl + "/api/v1/account/1", {
        params: this.requestBody,
        headers: {}
      }).then((res) => {

        this.data = res.data  //서버에서 데이터를 목록으로 보내므로 바로 할당하여 사용할 수 있다.

      }).catch((err) => {
        if (err.message.indexOf('Network Error') > -1) {
          alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
        }
      })
    }
  }
}
</script>