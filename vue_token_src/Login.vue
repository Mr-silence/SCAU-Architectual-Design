<!-- Login.vue -->

<template>
    <div>
        <h1>登录页面</h1>
        <form @submit.prevent="login">
            <label for="username">用户名：</label>
            <input v-model="username" type="text" id="username" required />
            <br />
            <label for="password">密码：</label>
            <input v-model="password" type="password" id="password" required />
            <br />
            <button type="submit">登录</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            username: "",
            password: "",
        };
    },
    methods: {
        async login() {
            try {
                // 调用 /api/Auth 接口获取 token
                const response = await fetch("http://47.115.44.145:7000/api/auth", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        username: this.username,
                        password: this.password,
                    }),
                });

                if (response.ok) {
                    const { token } = await response.json();

                    // 跳转到下一个页面，并将 token、username 和 password 传递给路由参数
                    this.$router.push({ path: "/dashboard", query: { token, username: this.username, password: this.password } });
                } else {
                    console.error("登录失败");
                }
            } catch (error) {
                console.error("登录时出错：", error);
            }
        },
    },
};
</script>
