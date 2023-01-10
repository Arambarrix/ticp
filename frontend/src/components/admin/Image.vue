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
    <div class="w-full rounded">
        <img :src="url" alt="image" />
    </div>
</template>