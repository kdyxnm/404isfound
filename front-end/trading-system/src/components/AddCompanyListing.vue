<template>
  <el-form ref="form" :model="form" label-width="120px">
    <el-form-item label="Company Name">
      <el-input v-model="companyName" :disabled="true" />
    </el-form-item>
    <el-form-item label="Share Price">
      <el-input v-model.number="form.sharePrice" />
    </el-form-item>
    <el-form-item label="Share Amount">
      <el-input v-model.number="form.shareAmount" />
    </el-form-item>
    <el-form-item label="Share Type">
      <el-select v-model="form.shareType" placeholder="Select share type">
        <el-option label="A" value="A"></el-option>
        <el-option label="B" value="B"></el-option>
        <el-option label="C" value="C"></el-option>
        <el-option label="D" value="D"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">Submit</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { ref } from 'vue';
import { useStore } from 'vuex';

export default {
  name: 'AddCompanyListing',
  setup() {
    // const store = useStore();
    // const companyName = ref(store.state.companyName); // Replace with the correct path to your company name in the store
    const companyName = "Dummy Name";
    const form = ref({
      sharePrice: null,
      shareAmount: null,
      shareType: '',
    });

    const onSubmit = () => {
      console.log('Form data:', form.value);
      // Here you would handle the form submission, e.g., send the data to your server
    };

    return {
      companyName,
      form,
      onSubmit,
    };
  },
};
</script>

<style>
.el-form {
  max-width: 400px;
  margin: auto;
  margin-top: 4em;
}
</style>
