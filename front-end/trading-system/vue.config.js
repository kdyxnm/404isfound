const { defineConfig } = require('@vue/cli-service');
const AutoImport = require('unplugin-auto-import/webpack');
const Components = require('unplugin-vue-components/webpack');
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers');

module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    plugins: [
      AutoImport({
        imports: [
          'vue',
          'vue-router',
          '@vueuse/core',
        ],
        dts: 'src/auto-imports.d.ts',
      }),
      Components({
        resolvers: [ElementPlusResolver({ importStyle: 'css' })],
      }),
    ],
  },
  pwa: {
    iconPaths: {
      favicon32: 'page-not-found.png',
      favicon16: 'page-not-found.png',
      appleTouchIcon: 'page-not-found.png',
      maskIcon: 'page-not-found.png',
      msTileImage: 'page-not-found.png',
    },
  },
});
