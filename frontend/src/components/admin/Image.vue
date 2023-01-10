<script setup>
    import { computed } from 'vue'

    const props = defineProps({ 'id': Number, 'url': String});

    var cssVars = computed(() => {
        return {
            '--bg-color': props.color,
        }
    });

</script>
<template>
    <div class="w-full rounded relative">
        <div to="" class="absolute text-center top-3 right-3 shadow-sm">
            <i class=" h-8 w-8 p-2 rounded-full bg-red-900 fa-regular fa-trash text-white cursor-pointer font-bold" @click="toggleModal" title="modifier"></i>
        </div>
        <img :src="url" alt="image" />
    </div>
</template>