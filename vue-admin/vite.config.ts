import {build, defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  build:{
    chunkSizeWarningLimit: 1500
  },
  plugins: [vue()],
  server: {
    port: 18000
  }
})
