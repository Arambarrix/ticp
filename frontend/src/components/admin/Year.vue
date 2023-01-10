<script setup>
import { RouterLink } from "vue-router";

const props = defineProps({'year':String,'link':String, 'actif':String , 'classes':{'actif':String , 'other':String}});

</script>
<template>
    <RouterLink :to="props.link" class="text-center p-2" :class="props.actif == props.year ? props.classes.actif : props.classes.other"> 
       {{ props.year }}
    </RouterLink>
</template>