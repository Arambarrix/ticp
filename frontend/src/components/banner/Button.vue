<script setup>
import { RouterLink } from "vue-router";

const props = defineProps({'button_info':{'link':String,'text':String}, 'actif':String , 'actif_class':String , 'other_class':String});

</script>
<template>
    <RouterLink :to="props.button_info.link" class="text-center w-1/3 p-2" :class="props.actif == props.button_info.text ? props.actif_class : props.other_class"> 
        <slot>
            Participer <!-- default content -->
        </slot>
    </RouterLink>
</template>