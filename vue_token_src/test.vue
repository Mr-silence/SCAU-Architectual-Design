<!-- Dashboard.vue -->

<template>
  <div>
    <h1 v-if="apiError">Error Code: {{ apiError }}</h1>
    <div v-if="token">
      <p>Token: {{ token }}</p>
        <p>username: {{ username }}</p>
        <p>password: {{ password }}</p>
      <p v-if="!apiError">{{ apiResponse }}</p>
    </div>
    <div v-else>
      <p>No token provided</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      token: this.$route.query.token || null,
      apiResponse: "",
      apiError: null,
        username: this.$route.query.username || null,
        password: this.$route.query.password || null,
    };
  },
  async mounted() {
    if (!this.token) {
      console.error("No token provided");
      return;
    }

      try {
          const response = await fetch("http://47.115.44.145:7000/api/Auth/test4", {
              method: "POST",
              headers: {
                  Authorization: `Bearer ${this.token}`,
                  "Content-Type": "application/json",
              },
              body: JSON.stringify({
                  username: this.username,
                  password: this.password,
              }),
          });

      if (response.ok) {
        const data = await response.json();
        this.apiResponse = data.message;
      } else {
        this.apiError = response.status;
        console.error("API request failed:", response.status);
      }
    } catch (error) {
      this.apiError = "Unknown Error";
      console.error("Error during API request:", error);
    }
  },
};
</script>
