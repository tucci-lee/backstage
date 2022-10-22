<template>
  <div>
    <el-menu router
             background-color="#545c64"
             text-color="#fff"
             active-text-color="#ffd04b">
      <el-sub-menu v-for="menu in menus" :key="menu.id" :index="menu.url?menu.url:menu.id">
        <template #title>
          <span>{{ menu.name }}</span>
        </template>
        <el-menu-item v-for="cmenu in menu.children" :key="cmenu.id" :index="cmenu.url">
          <template #title>{{ cmenu.name }}</template>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script>
import {ownedMenusUrl} from "@/plugins/request";

export default {
  name: "Menu",
  data() {
    return {
      menus: []
    }
  },
  created() {
    this.loadMenus();
  },
  methods: {
    /**
     * 加载菜单
     */
    loadMenus() {
      this.$axios.get(ownedMenusUrl)
          .then(res => {
            if (!res.status) {
              return;
            }
            this.menus = res.data;
          })
    },
  }
}
</script>

<style scoped>

</style>