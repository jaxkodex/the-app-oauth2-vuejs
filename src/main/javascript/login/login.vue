<template>
<div class="container">
    <form @submit.prevent="onSubmit()">
        <input type="text" name="username" v-model="username" />
        <input type="password" name="password" v-model="password" />
        <button type="submit">Enviar</button>
    </form>
</div>
</template>
<script>
import { login } from '../model/user'
export default {
    data: function () {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        onSubmit: function () {
            // login(this.username, this.password)
        }
    }
}
</script>