<script setup>
    import YearVue from './Year.vue';
    import { reactive } from 'vue'

    const props = defineProps({'tournois':Object, 'actif':String, 'base_link':String});
    const classes = reactive({ actif:"text-white font-extra-bold",  other:"text-white text-opacity-70"})

</script>
<template>

    <div class="flex flex-row flex-nowrap space-x-2 bg-clear-brown text-white items-center justify-between rounded-t-xl px-3 py-1 w-full">
        <div class="divide-x divide-solid divide-d-color flex justify-start space-x-5 overflow-x-auto ">
            <YearVue v-for="tournoi in props.tournois" :year=tournoi.id :actif="props.actif" :link="props.base_link+tournoi.id" :classes="classes"/>
        </div>
    </div>
</template>