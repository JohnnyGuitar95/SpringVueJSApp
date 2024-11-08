<template>
  <main>
    <Navbar/>
    <div class="my-5">
      <div class="mx-auto w-25 " style="max-width:100%;">
        <h2 class="text-center mb-3">Добавить отдел</h2>
        <form @submit.prevent="addDepartment">
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="name" class="form-label">Название отдела</label>
              <input id="name" type="text" name="name" class="form-control" placeholder="Название отдела" required
                     v-model="department.name">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="note" class="form-label">Примечание</label>
              <input id="note" type="text" name="note" class="form-control" placeholder="Примечание" required
                     v-model="department.note">
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group mb-3">
              <label for="parentDepartment" class="form-label">Родительский отдел</label>
              <select id="parentDepartment" class="form-control" v-model="department.parentDepartmentId">
                <option value="">Нет</option>
                <option v-for="dept in departments" :key="dept.id" :value="dept.id">{{ dept.name }}</option>
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12 form-group">
              <input class="btn btn-primary w-100" type="submit" value="Добавить">
            </div>
          </div>
          <div>
            <p v-if="message" class="text-success">{{ message }}</p>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>
import Navbar from '../../components/Navbar.vue';

export default {
  name: 'AddDepartment',
  components: {
    Navbar
  },

  data() {
    return {
      department: {
        name: '',
        note: '',
        parentDepartmentId: null
      },
      departments: [],
      message: ''
    }
  },

  created() {
    this.fetchDepartments();
  },

  methods: {
    fetchDepartments() {
      fetch('http://localhost:7000/departmentsApi/departments')
          .then(response => response.json())
          .then(data => {
            this.departments = data;
          })
          .catch(error => console.error('Error fetching departments:', error));
    },

    addDepartment() {
      fetch('http://localhost:7000/departmentsApi/add', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(this.department)
      })
          .then(response => {
            if (response.ok) {
              this.message = 'Отдел успешно добавлен!';
              this.clearForm();
              this.$router.push("/departmentsHome");
            } else {
              throw new Error('Ошибка при добавлении отдела');
            }
          })
          .catch(error => {
            console.error('Error adding department:', error);
            this.message = 'Произошла ошибка при добавлении отдела';
          });
    },

    clearForm() {
      this.department = {
        name: '',
        note: '',
        parentDepartmentId: null
      };
    }
  }
}
</script>