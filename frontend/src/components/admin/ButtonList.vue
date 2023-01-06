<script setup>
    import ButtonVue from '../banner/Button.vue';
    import { reactive } from 'vue'

    const props = defineProps({'list_text_links':Object, 'actif':String, 'is_fully_rounded':Boolean});
    const classes = reactive({ actif_class: "text-white bg-blue-900 rounded-full", other_class:"text-white bg-indigo-400 rounded-full"})

    if(!props.is_fully_rounded){


        classes.actif_class ="text-white bg-blue-800 rounded-xl"
        classes.other_class="text-white bg-blue-600 rounded-xl"
    }

</script>
<template>
    <div class="flex flex-row space-x-3"> 
        <ButtonVue v-for="button in props.list_text_links" v-bind="{'button_info':button, 'actif':actif, 'actif_class':classes.actif_class, 'other_class':classes.other_class}">{{button.text}}</ButtonVue>
    </div>
</template>