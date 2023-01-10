<script setup>
    import ImageView from './Image.vue';

    const props = defineProps({ 'images': Object });

</script>
<template>
    <div class="container mx-auto">
        <div class="lg:gap-2 lg:grid lg:grid-cols-3">
            <ImageView v-for="image in images"  v-bind="image" />
        </div>
    </div>
</template>