<template>
  <div class="container">
    <el-table :data="customers" style="width: 100%">
      <el-table-column prop="username" label="Username"></el-table-column>
      <el-table-column label="Action">
        <template #default="scope">
          <el-button type="primary" @click="viewPortfolio(scope.row.id)">View Portfolio</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  props: {
    customers: {
      type: Array,
      default: () => [],
    },
  },


  methods: {
    viewPortfolio(customerId) {
      this.$emit('view-portfolio', customerId);
    },
  },
};
</script>

<style scoped lang="scss">
.container {
  padding: 20px;
}
</style>
