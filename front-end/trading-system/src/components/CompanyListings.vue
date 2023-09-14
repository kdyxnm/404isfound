<template>
  <div class="container">
    <el-table :data="companyListings" style="width: 100%">
      <el-table-column prop="listId" label="List ID"></el-table-column>
      <el-table-column prop="price" label="Price"></el-table-column>
      <el-table-column prop="amount" label="Amount"></el-table-column>
      <el-table-column prop="companyId" label="Company ID"></el-table-column>
      <el-table-column prop="companyName" label="Company Name"></el-table-column>
      <el-table-column v-if="displayType == 'Buy'" label="Purchase">
        <template #default="scope">
          <el-button type="primary" @click="buyShare(scope.row.listId)">Buy</el-button>
        </template>
      </el-table-column>
      <el-table-column v-if="displayType == 'Sell'" label="Sell">
        <template #default="scope">
          <el-button type="primary" @click="sellShare(scope.row.listId)">Sell</el-button>
        </template>
      </el-table-column>
      <el-table-column v-if="displayType == 'Delete'" label="Delete">
        <template #default="scope">
          <el-popconfirm title="Are you sure to delete this?" @confirm="deleteListing(scope.row.listId)">
            <template #reference>
              <el-button type="primary">Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  props: {
    companyListings: {
      type: Array,
      default: () => [],
    },
    displayType: {
      type: String,
      default: null,
    },
  },
  methods: {
    buyShare(listId) {
      this.$emit('buy-share', listId);
    },
    sellShare(listId) {
      this.$emit('sell-share', listId);
    },
    deleteListing(listId) {
      this.$emit('delete-listing', listId);
    }
  },
};
</script>

<style scoped lang="scss">
.container {
  padding: 20px;
}
</style>
