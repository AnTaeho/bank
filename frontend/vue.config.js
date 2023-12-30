const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  outputDir: "../src/main/resources/static",
  devServer: {
    proxy: {
      '/test': {
        target: 'http://localhost:8081',
        changeOrigin: true
      },
      '/api/v1': {
        target: 'http://localhost:8081',
        changeOrigin: true
      }
    }
  }
})